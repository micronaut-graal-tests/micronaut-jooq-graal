package example.jooq.repositories;

import example.jooq.domain.Owner;
import org.jooq.DSLContext;
import org.simpleflatmapper.jdbc.DynamicJdbcMapper;
import org.simpleflatmapper.jdbc.JdbcMapperFactory;

import javax.inject.Singleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static example.jooq.domain.Tables.OWNER;
import static example.jooq.domain.tables.Pet.PET;

@Singleton
public class OwnerRepository {

    private final DSLContext context;
    private final DynamicJdbcMapper<Owner> mapper;

    public OwnerRepository(DSLContext context) {
        this.context = context;
        this.mapper = JdbcMapperFactory.newInstance()
                .addKeys("id", "pet_id")
                .newMapper(Owner.class);
    }

    public List<Owner> findAll() {
        return context.selectFrom(OWNER).fetchInto(Owner.class);
    }

    public Optional<Owner> findByName(String name) throws SQLException {
        ResultSet rs = context.select(OWNER.ID, OWNER.NAME, OWNER.AGE)
                .from(OWNER)
                .join(PET)
                .on(PET.OWNER_ID.eq(OWNER.ID))
                .where(OWNER.NAME.eq(name))
                .fetchResultSet();

        return mapper.stream(rs).findFirst();
    }
}
