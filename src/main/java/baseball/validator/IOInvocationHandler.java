package baseball.validator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * IOException 등 입력 과정에서 예외가 터질 경우 IllegalArgumentException 으로 변환하기 위한 프록시 클래스
 */
public class IOInvocationHandler implements InvocationHandler {

    private final Object target;

    public IOInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
