package micronaut.example.repositories;

import jakarta.inject.Singleton;
import micronaut.example.domain.Owner;
import org.jooq.DSLContext;
import org.jooq.ResultQuery;
import org.simpleflatmapper.jdbc.DynamicJdbcMapper;
import org.simpleflatmapper.jdbc.JdbcMapperFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static micronaut.example.domain.Tables.OWNER;

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
        ResultQuery<?> query = context.select(OWNER.ID, OWNER.NAME, OWNER.AGE)
                .from(OWNER)
                .where(OWNER.NAME.eq(name));

        try (ResultSet rs = query.fetchResultSet()) {
            return mapper.stream(rs).findFirst();
        }
    }
}
