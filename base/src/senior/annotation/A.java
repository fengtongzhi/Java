package senior.annotation;

public @interface A {
    String name();
    int age() default 18;
    String[] schools();
}
