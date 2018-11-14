package modules;

import models.Freight;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by isatimur on 8/9/16.
 */
public class SqlQueryWSFreight {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static Logger logger = LoggerFactory.getLogger(SqlQueryWSFreight.class);

    /*
      Replicated cache name to store departments.
      */
    private static final String WS_FREIGHT_CACHE_NAME = Freight.class.getSimpleName() + "-freight";

    /**
     * This is an entry point of SqlQueryEmployees, the ignite configuration lies upon resources directory as
     * example-ignite.xml.
     * @param args Command line arguments, none required.
     */
    public static void main(String[] args) throws Exception {

        //The ignite configuration lies below resources directory as example-ignite.xml.
        Ignite ignite = Ignition.start("https://raw.githubusercontent.com/apache/ignite/master/examples/config/example-cache.xml");

        logger.info("Start. Sql query example.");

        CacheConfiguration<Long, Freight> wsFreightCacheCfg = new CacheConfiguration<>(WS_FREIGHT_CACHE_NAME);

        wsFreightCacheCfg.setCacheMode(CacheMode.REPLICATED);
        wsFreightCacheCfg.setIndexedTypes(Long.class, Freight.class);
//            ignite.

        try (
                IgniteCache<Long, Freight> wsFreightIgniteCache = Ignition.ignite().getOrCreateCache(wsFreightCacheCfg);
        ) {
            // Populate cache.
            wsFreightIgniteCache.clear();

            // Departments
            Freight freight1 = new Freight("00000", "", 1l, "---", "0", "000000");
            Freight freight2 = new Freight("00001", "", 2l, "---", "0", "000000");
            Freight freight3 = new Freight("00002", "", 3l, "---", "0", "000000");
            Freight freight4 = new Freight("00003", "", 4l, "---", "0", "000000");

            wsFreightIgniteCache.put(freight1.getKey(), freight1);
            wsFreightIgniteCache.put(freight2.getKey(), freight2);
            wsFreightIgniteCache.put(freight3.getKey(), freight3);
            wsFreightIgniteCache.put(freight4.getKey(), freight4);
            Thread.sleep(1000);

//            IgniteCache<Long, Freight> cache = Ignition.ignite().cache(WS_FREIGHT_CACHE_NAME);

            // Example for SQL-based querying employees based on salary ranges.
            // Create query which selects salaries based on range.
//            SqlFieldsQuery qry = new SqlFieldsQuery("select name from Freight");

            SqlQuery<Long, Freight> qry = new SqlQuery<>(Freight.class, "groupp > ? and groupp <= ?");

            // Execute queries for salary ranges.
            logDecorated("==WsFreight from 2 to 4 ==", wsFreightIgniteCache.query(qry.setArgs(0l,2l)).getAll());

            log("Sql query example finished.");
        }
    }

    /**
     * @param parseDateText
     * @return
     */
    private static LocalDate localDateOf(String parseDateText) {
        return LocalDate.parse(parseDateText, formatter);
    }

    /**
     * Prints message to logger.
     * @param msg String.
     */
    private static void log(String msg) {
        logger.info("\t" + msg);
    }

    /**
     * Prints message to logger.
     * @param msg String.
     */
    private static void log(String msg, Iterable<?> col) {
        logger.info("\t" + msg);
        col.forEach(c -> logger.info("\t\t" + c));
    }

    /**
     * Prints message and resultset to logger.
     * @param msg String.
     * @param col Iterable
     */
    private static void logDecorated(String msg, Iterable<?> col) {
        logger.info("\t" + msg);
        col.forEach(c -> logger.info("\t\t" + c));
    }

}
