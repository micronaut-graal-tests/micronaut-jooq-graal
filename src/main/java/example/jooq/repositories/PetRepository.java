package example.jooq.repositories;

import example.jooq.domain.NameDTO;
import example.jooq.domain.PetWithOwner;
import org.jooq.DSLContext;
import org.jooq.ResultQuery;
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
public class PetRepository {

    private final DSLContext context;
    private final DynamicJdbcMapper<PetWithOwner> mapper;

    public PetRepository(DSLContext context) {
        this.context = context;
        this.mapper = JdbcMapperFactory.newInstance()
                .addKeys("id", "owner_id")
                .newMapper(PetWithOwner.class);
    }

    public List<NameDTO> list() {
        return context.select(PET.NAME).from(PET).fetchInto(NameDTO.class);
    }

    public Optional<PetWithOwner> findByName(String name) throws SQLException {
        ResultQuery<?> query = context.select(PET.ID, PET.NAME, PET.TYPE)
                .select(OWNER.ID.as("owner_id"), OWNER.NAME.as("owner_name"), OWNER.AGE.as("owner_age"))
                .from(PET)
                .join(OWNER)
                .on(PET.OWNER_ID.eq(OWNER.ID))
                .where(PET.NAME.eq(name));

        try (ResultSet rs = query.fetchResultSet()) {
            return mapper.stream(rs).findFirst();
        }
    }
}
