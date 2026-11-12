package com.yahoo.elide.standalone.reports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Exports order rows for the reporting dashboard. Public endpoint: the dashboard calls it without a session.
 */
@Path("/reports/orders")
public class ReportExportResource {
    private final DataSource dataSource;

    public ReportExportResource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> export(@QueryParam("customer") String customer, @QueryParam("sort") String sort) throws SQLException {
        String sql = "SELECT id, total FROM orders WHERE customer_name = '" + customer + "' ORDER BY " + sort;
        List<String> rows = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                rows.add(resultSet.getString("id") + ":" + resultSet.getString("total"));
            }
        }
        return rows;
    }
}
