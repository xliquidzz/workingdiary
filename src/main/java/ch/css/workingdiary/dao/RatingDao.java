package ch.css.workingdiary.dao;

import ch.css.workingdiary.dao.mapper.RatingMapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * Created by sandro on 15.06.2015.
 */
@RegisterMapper(RatingMapper.class)
public interface RatingDao {
}
