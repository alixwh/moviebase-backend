package ee.taltech.iti0302.webproject.api.entities;

import ee.taltech.iti0302.webproject.entities.Actor;
import ee.taltech.iti0302.webproject.entities.Director;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter @Setter
@RequiredArgsConstructor
public class CreditsList {
    private List<Actor> cast;
    private Set<Director> crew;
}
