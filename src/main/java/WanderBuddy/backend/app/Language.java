package WanderBuddy.backend.app;

public enum Language {
    KOREAN,
    ENGLISH;

    /**
     * enum value를 소문자로 변환해서 리턴합니다.
     *
     * @return lower case
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
