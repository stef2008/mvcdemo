package com.nowcoder.mvcdemo.validator;

import com.nowcoder.mvcdemo.entity.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target == null) {
            return;
        }
        User user = (User) target;
        if (StringUtils.isEmpty(user.getUsername())) {
            errors.rejectValue("username", null, "账号不能为空！");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            errors.rejectValue("password", null, "密码不能为空！");
        }
    }

}
