package com.ntp.testyoutube.models.youtube.playerResponse;

import java.io.Serializable;
import java.util.List;

public class Text implements Serializable {
    private List<RunsItem> runs;

    public List<RunsItem> getRuns() {
        return runs;
    }

    public void setRuns(List<RunsItem> runs) {
        this.runs = runs;
    }

    @Override
    public String toString() {
        return
                "Text{" +
                        "runs = '" + runs + '\'' +
                        "}";
    }
}