package example.jooq;

import example.jooq.domain.tables.records.OwnerRecord;
import example.jooq.domain.tables.records.PetRecord;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.core.annotation.TypeHint;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

import static example.jooq.domain.Tables.OWNER;
import static example.jooq.domain.Tables.PET;

@Singleton
@TypeHint(
        value = {PetRecord.class, OwnerRecord.class},
        accessType = TypeHint.AccessType.ALL_DECLARED_CONSTRUCTORS
)
public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    private final DSLContext context;

    public Application(DSLContext context) {
        this.context = context;
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }

    @EventListener
    void init(StartupEvent event) {
        if (LOG.isInfoEnabled()) {
            LOG.info("Populating data");
        }

        OwnerRecord fred = context.newRecord(OWNER);
        fred.setName("Fred");
        fred.setAge(45);
        fred.store();

        OwnerRecord barney = context.newRecord(OWNER);
        barney.setName("Barney");
        barney.setAge(40);
        barney.store();

        PetRecord dino = context.newRecord(PET);
        dino.setName("Dino");
        dino.setOwnerId(fred.getId());
        dino.store();

        PetRecord babyPuss = context.newRecord(PET);
        babyPuss.setName("Baby Puss");
        babyPuss.setOwnerId(fred.getId());
        babyPuss.store();

        PetRecord hoppy = context.newRecord(PET);
        hoppy.setName("Hoppy");
        hoppy.setOwnerId(barney.getId());
        hoppy.store();
    }
}
