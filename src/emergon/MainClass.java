package emergon;

import emergon.dao.ActorDao;
import emergon.entity.Actor;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author user
 */
public class MainClass {

    public static void main(String[] args) throws SQLException {
        ActorDao actorDao = new ActorDao();
//        List<Actor> actors = actorDao.findAll();
//        for(Actor actor:actors){
//            System.out.println(actor);
//        }
        Actor actor = new Actor("Nick", "Nickolson", LocalDateTime.now());
        actorDao.create(actor);
    }

}
