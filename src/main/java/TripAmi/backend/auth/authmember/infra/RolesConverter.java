package TripAmi.backend.auth.authmember.infra;

import TripAmi.backend.auth.authmember.domain.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Converter
public class RolesConverter implements AttributeConverter<Set<Role>, String> {
    @Override
    public String convertToDatabaseColumn(Set<Role> attribute) {
        return attribute.stream().map(Enum::name)
            .sorted()
            .collect(Collectors.joining(" "));
    }

    @Override
    public Set<Role> convertToEntityAttribute(String dbData) {
        String[] roles = dbData.split(" ");
        Set<Role> roleSet = new TreeSet<>();
        for (String role : roles) {
            roleSet.add(Role.valueOf(role));
        }
        return roleSet;
    }
}
