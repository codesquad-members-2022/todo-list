package codesquad.be.todoserver.repository;

import codesquad.be.todoserver.domain.History;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository {

	Optional<List<History>> findAllHistory();

}
