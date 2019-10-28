package spring_boot.EXAMPLE_SPRING_BOOT.SECURITY_JDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Query_JdbcPeople {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createTable() {
        String sql = "CREATE TABLE people(id int primary key , name varchar(100), surname varchar(100), height int, wage int, age int, email varchar(100))";
        jdbcTemplate.update(sql);

        String sql2 = "CREATE TABLE oauth_refresh_token ( token_id VARCHAR(255), token LONG VARBINARY, authentication LONG VARBINARY );";
        jdbcTemplate.update(sql2);

    }


    public void addPeople(People people) {
        String sql = "INSERT INTO people VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]
                {
                        people.getId(),
                        people.getName(),
                        people.getSurname(),
                        people.getHeight(),
                        people.getWage(),
                        people.getAge(),
                        people.getEmail()

                });
    }


    public People readPeople(String name) {
        String sql = "SELECT name,surname,age FROM people WHERE name = ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql,name);

        People people = new People();
        for(Map<String,Object> map : maps)
        {
            people.setId((Integer) map.get("id"));
            people.setName((String) map.get("name"));
            people.setSurname((String) map.get("surname"));
            people.setHeight((Integer) map.get("height"));
            people.setWage((Integer) map.get("wage"));
            people.setAge((Integer) map.get("age"));
            people.setEmail((String) map.get("email"));
        }
        return people;
    }

    public void updatePeople(People people)
    {
        String sql = "UPDATE people SET name = ? , surname = ? WHERE id = ?";
        jdbcTemplate.update(
                sql, people.getName(),
                people.getSurname(),
                people.getId() );

    }


    public void deletePeople()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println( "Are you want to DELETE ALL RECORD? PRESS Y/N");
        String choose = sc.nextLine();

        if(choose.equals("Y"))
        {
            String sql = "DELETE * FROM people";
            jdbcTemplate.update(sql);
        }
        else
        {
            System.out.println("ALL RECORD IN TABLE");
        }

    }


}