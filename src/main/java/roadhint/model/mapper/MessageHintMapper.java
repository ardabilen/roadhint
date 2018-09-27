package roadhint.model.mapper;//package roadhint.model.mapper;
//
//import roadhint.enums.HintType;
//import roadhint.model.MessageHint;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// * Created by ardab on 26.06.2017.
// */
//public class MessageHintMapper implements RowMapper<MessageHint> {
//
//
//    @Override
//    public MessageHint mapRow(ResultSet rs, int rowNum) throws SQLException {
//        MessageHint messageHint = new MessageHint();
//
//        messageHint.setId(rs.getLong("hint_id"));
//        messageHint.setType(HintType.MESSAGE);
//        messageHint.setErrorMessage(rs.getString("message"));
//
//        return messageHint;
//
//    }
//}
