package cs.miu.edu.service;

import cs.miu.edu.domain.CustomSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.*;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 9:35 PM
 * @project webstore
 */
@Service
public class NextSequenceService {
    @Autowired
    private MongoOperations mongo;

    public int getNextSequence(String seqName)
    {
        CustomSequence counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                CustomSequence.class);
        return counter.getSeq();
    }
}