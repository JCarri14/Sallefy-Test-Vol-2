package salle.android.projects.service_test.controller.callbacks;

import salle.android.projects.service_test.model.Track;

public interface TrackListCallback {
    void onTrackSelected(Track track);
    void onTrackSelected(int index);
}
