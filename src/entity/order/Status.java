package entity.order;

/**
 * @author a.shestovsky
 */

public enum Status implements StatusToString{
    OPEN {
        @Override
        public String getAsString() {
            return "Открыт";
        }
    }, IN_PROGRESS {
        @Override
        public String getAsString() {
            return "В обработке";
        }
    }, COMPLETED {
        @Override
        public String getAsString() {
            return "Доставлен";
        }
    }, CLOSED {
        @Override
        public String getAsString() {
            return "Отменен";
        }
    }
}