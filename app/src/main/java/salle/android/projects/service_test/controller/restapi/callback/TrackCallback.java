package salle.android.projects.service_test.controller.restapi.callback;

import java.util.List;

import salle.android.projects.service_test.model.Track;


public interface TrackCallback extends FailureCallback {
    void onTracksReceived(List<Track> tracks);
    void onNoTracks(Throwable throwable);
    void onPersonalTracksReceived(List<Track> tracks);
    void onUserTracksReceived(List<Track> tracks);
    void onCreateTrack();
}
