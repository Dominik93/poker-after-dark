package com.slusarz.pokerafterdark.domain.validation;

import com.slusarz.pokerafterdark.domain.tournament.Place;
import com.slusarz.pokerafterdark.domain.tournament.TournamentParticipant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CorrectPlacesValidator implements ConstraintValidator<CorrectPlaces, List<TournamentParticipant>> {

    @Override
    public boolean isValid(List<TournamentParticipant> tournamentParticipants, ConstraintValidatorContext constraintValidatorContext) {
        List<Place> places = getSortedPlaces(tournamentParticipants);
        if (places.isEmpty()) {
            return false;
        }
        if (!startWithOne(places)) {
            return false;
        }
        return isOrdered(places);
    }

    private boolean startWithOne(List<Place> places) {
        return places.get(0).getPlace() == 1;
    }

    private boolean isOrdered(List<Place> places) {
        for (int i = 0; i < places.size() - 1; i++) {
            if (1 != places.get(i + 1).getPlace() - places.get(i).getPlace()) {
                return false;
            }
        }
        return true;
    }

    private List<Place> getSortedPlaces(List<TournamentParticipant> tournamentParticipants) {
        return tournamentParticipants.stream()
                .map(TournamentParticipant::getPlace)
                .sorted(Comparator.comparingInt(Place::getPlace))
                .collect(Collectors.toList());
    }

}
