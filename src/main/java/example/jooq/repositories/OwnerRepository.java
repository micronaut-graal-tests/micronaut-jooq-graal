package example.jooq.repositories;

import example.jooq.domain.Owner;
import example.jooq.domain.OwnerWithPets;
import org.jooq.DSLContext;
import org.simpleflatmapper.jdbc.DynamicJdbcMapper;
import org.simpleflatmapper.jdbc.JdbcMapperFactory;

import javax.inject.Singleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static example.jooq.domain.Tables.OWNER;
import static example.jooq.domain.tables.Pet.PET;

@Singleton
public class OwnerRepository {

    private final DSLContext context;
    private final DynamicJdbcMapper<OwnerWithPets> mapper;

    public OwnerRepository(DSLContext context) {
        this.context = context;
        this.mapper = JdbcMapperFactory.newInstance()
                .addKeys("id", "pet_id")
                .newMapper(OwnerWithPets.class);
    }

    public List<Owner> findAll() {
        return context.selectFrom(OWNER).fetchInto(Owner.class);
    }

    public Optional<OwnerWithPets> findByName(String name) throws SQLException {
        ResultSet rs = context.select(OWNER.ID, OWNER.NAME, OWNER.AGE)
                .select(PET.ID.as("pet_id"), PET.NAME.as("pet_name"))
                .from(OWNER)
                .join(PET)
                .on(PET.OWNER_ID.eq(OWNER.ID))
                .where(OWNER.NAME.eq(name))
                .fetchResultSet();

        List<OwnerWithPets> list = mapper.stream(rs).collect(Collectors.toList());

        if (list.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(list.get(0));
        }
    }
}
