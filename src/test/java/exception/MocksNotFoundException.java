package exception;

public class MocksNotFoundException extends Exception {
    public MocksNotFoundException(String message) {
        super(message);
    }

    public MocksNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MocksNotFoundException(Throwable cause) {
        super(cause);
    }
}
