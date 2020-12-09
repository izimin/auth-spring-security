package org.psu.auth.service;

import org.psu.auth.exceptions.ValidationException;
import org.psu.auth.model.dto.UserRegistrationDto;
import org.psu.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationService {

    private final UserRepository userRepository;

    @Autowired
    public ValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
    private static final String phoneRegex = "^((8|\\+7)[\\- ]?)(\\(\\d{3}\\)[\\- ]?|\\d{3}[\\- ]?)(([\\- ]?\\d){7})";

    private boolean checkEmail(String email) {
        return Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE).matcher(email).matches();
    }

    private boolean checkPhone(UserRegistrationDto userData) {
        Matcher mValue = Pattern.compile(phoneRegex, Pattern.CASE_INSENSITIVE).matcher(userData.getPhone());
        if (mValue.matches()) {
            userData.setPhone(String.format("%s%s%s",mValue.group(3), mValue.group(4), mValue.group(5)));
        }
        return mValue.matches();
    }

    public void validateUserData(UserRegistrationDto userRegistrationDto) throws ValidationException {
        if (userRegistrationDto.getLogin() == null || userRegistrationDto.getLogin().isEmpty())
            throw  new ValidationException("Логин не может быть пустым");
        if (userRepository.findByLogin(userRegistrationDto.getLogin()) != null)
            throw  new ValidationException("Такой пользователь уже существует");
        if (userRegistrationDto.getFirstName() == null || userRegistrationDto.getFirstName().isEmpty())
            throw  new ValidationException("Вы не заполнили имя");
        if (userRegistrationDto.getLastName() == null || userRegistrationDto.getLastName().isEmpty())
            throw  new ValidationException("Вы не заполнили фамилию");
        if (userRegistrationDto.getEmail() == null || userRegistrationDto.getEmail().isEmpty())
            throw  new ValidationException("Вы не заполнили e-mail");
        if (!checkEmail(userRegistrationDto.getEmail()))
            throw  new ValidationException("Неверный email (не подходит ни под один существующий шаблон)");
        if (userRepository.findByEmail(userRegistrationDto.getEmail()) != null)
            throw  new ValidationException("Пользователь с такой электронной почтой уже зарегистрирован");
        if (userRegistrationDto.getPhone() == null || userRegistrationDto.getPhone().isEmpty())
            throw  new ValidationException("Вы не заполнили телефон");
        if (!checkPhone(userRegistrationDto))
            throw  new ValidationException("Неверный телефонный номер");
        if (userRepository.findByPhone(Long.parseLong(userRegistrationDto.getPhone())) != null)
            throw  new ValidationException("Пользователь с таким телефоном уже зарегистрирован");
        if (userRegistrationDto.getPassword1() == null || userRegistrationDto.getPassword1().isEmpty())
            throw  new ValidationException("Пароль не может быть пустым");
        if (userRegistrationDto.getPassword2() == null || userRegistrationDto.getPassword2().isEmpty())
            throw  new ValidationException("Вы не ввели повторный пароль");
        if (!userRegistrationDto.getPassword1().equals(userRegistrationDto.getPassword2()))
            throw  new ValidationException("Пароли не совпадают");
    }
}
