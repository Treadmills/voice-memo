package com.ddz.aop;

import com.ddz.domain.entity.UserInfo;
import com.ddz.utils.base.R;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * Created by admin on 2019/11/20.
 */
@Aspect
@Component
public class ControllerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    /**
     * 定义拦截规则：拦截com.ddz.controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.ddz.controller..*(..))" +
            " && ( @annotation(org.springframework.web.bind.annotation.RequestMapping )" +
            " || @annotation(org.springframework.web.bind.annotation.GetMapping) " +
            " || @annotation(org.springframework.web.bind.annotation.PostMapping ))")
    public void controllerMethodPointcut() {
    }

    /**
     * 拦截器具体实现
     *
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("controllerMethodPointcut()") //指定拦截器规则；也可以直接把“execution(* com.ddz.........)”写进这里
    public Object Interceptor(ProceedingJoinPoint pjp) {
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        Object result = null;

        if (isLoginRequired(method)) {
            R loginResult = isLogin(request);
            if (loginResult.getStatus() != 0) {
                result = loginResult;
            }
        }
        if (result == null) {
            try {
                result = pjp.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                result = R.isFail(throwable.getMessage());
            }
        }
        return result;
    }

    /**
     * 判断一个方法是否需要登录
     *
     * @param method 方法
     * @return
     */
    private boolean isLoginRequired(Method method) {
        boolean result = false;
        if (method.isAnnotationPresent(LoginRequired.class)) {
            result = method.getAnnotation(LoginRequired.class).loginRequired();
        }
        return result;
    }

    //判断是否已经登录
    private R isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            return R.isUnauthorized();
        }
        return R.isOk();
    }
}
