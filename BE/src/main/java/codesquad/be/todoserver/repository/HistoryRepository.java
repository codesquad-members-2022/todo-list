package codesquad.be.todoserver.repository;

import codesquad.be.todoserver.domain.History;
import java.util.List;

public interface HistoryRepository {

	List<History> findAllHistory();

}
