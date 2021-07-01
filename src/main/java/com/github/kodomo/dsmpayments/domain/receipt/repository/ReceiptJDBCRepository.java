package com.github.kodomo.dsmpayments.domain.receipt.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReceiptJDBCRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CoinRowMapper mapper = new CoinRowMapper();

    public List<Long> boothCoinIncomeOfHour() {

        String query = "SELECT @hour_count:=@hour_count+1 as hour, " +
                "IFNULL((select sum(final_value) from tbl_receipt where  HOUR(created_at) = @hour_count GROUP BY HOUR(created_at)), 0) as coin " +
                "FROM tbl_receipt where @hour_count < 17 and booth_id is not null and final_value > 0;";
        jdbcTemplate.execute("set @hour_count:=7");
        return jdbcTemplate.query(query, mapper);
    }

    public List<Long> boothCoinExpensesOfHour() {
        String query = "SELECT @hour_count:=@hour_count+1 as hour, " +
                "IFNULL((select sum(final_value) from tbl_receipt where  HOUR(created_at) = @hour_count GROUP BY HOUR(created_at)), 0) as coin " +
                "FROM tbl_receipt where @hour_count < 17 and booth_id is not null and final_value < 0;";
        jdbcTemplate.execute("set @hour_count:=7");
        return jdbcTemplate.query(query, mapper);
    }

    private static class CoinRowMapper implements RowMapper<Long> {
        @Override
        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getLong("coin");
        }
    }

}
