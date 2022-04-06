package todo.list.domain;

import org.springframework.util.StringUtils;

public enum CardStatus {
    TODO("해야할 일"),
    IN_PROGRESS("하고 있는 일"),
    DONE("완료한 일");

    public final String string;

    CardStatus(String string) {
        this.string = string;
    }

    public static CardStatus from(String string) {
        if (StringUtils.hasText(string)) {
            string = string.toUpperCase();
        }

        if (TODO.toString().equals(string)) {
            return TODO;
        }
        if (IN_PROGRESS.toString().equals(string)) {
            return IN_PROGRESS;
        }
        if (DONE.toString().equals(string)) {
            return DONE;
        }
        return null;
    }
}
