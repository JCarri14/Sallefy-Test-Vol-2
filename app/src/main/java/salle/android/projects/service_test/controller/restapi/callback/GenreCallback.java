package salle.android.projects.service_test.controller.restapi.callback;

import java.util.ArrayList;

import salle.android.projects.service_test.model.Genre;
import salle.android.projects.service_test.model.Track;

public interface GenreCallback extends FailureCallback {

    void onGenresReceive(ArrayList<Genre> genres);
    void onTracksByGenre(ArrayList<Track> tracks);
}
