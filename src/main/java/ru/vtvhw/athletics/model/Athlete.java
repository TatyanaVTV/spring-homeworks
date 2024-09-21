package ru.vtvhw.athletics.model;

import io.micrometer.common.util.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Runner {
    String firstname;
    String surname;
    Gender gender;

    public Runner(String firstname, String surname, Gender gender) {
        if (StringUtils.isEmpty(firstname) || StringUtils.isEmpty(surname) || gender == null) {
            throw  new IllegalArgumentException(
                    "Firstname, surname and gender should be defined! ["
                            + surname + ", " + firstname + ", " + gender + "]");
        }
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        return firstname.hashCode() + surname.hashCode() + gender.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj.getClass().getComponentType().isAssignableFrom(Runner.class)) {
            if (surname.equals(((Runner) obj).getSurname())
                    && firstname.equals(((Runner) obj).getFirstname())) {
                return true;
            }
        }
        return super.equals(obj);
    }
}
