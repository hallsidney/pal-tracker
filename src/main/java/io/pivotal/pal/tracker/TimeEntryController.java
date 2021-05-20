package io.pivotal.pal.tracker;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TimeEntryController {

    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository){this.timeEntryRepository = timeEntryRepository;}

//    public TimeEntryRepository getTimeEntry(){
//        return this.timeEntryRepository;
//    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(TimeEntry timeEntry){
        return ResponseEntity
                .created(null)
                .body(this.timeEntryRepository
                        .create(timeEntry));
    }
    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId){
        TimeEntry timeEntryFound = timeEntryRepository.find(timeEntryId);//find(timeEntryId);
        if(timeEntryFound == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(timeEntryFound);
    }

    public ResponseEntity<List<TimeEntry>> list(){
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        return ResponseEntity.ok(timeEntryList);
    }

    public ResponseEntity<TimeEntry> update(Long timeEntryId, TimeEntry timeEntry){
        TimeEntry timeEntryUpdate = timeEntryRepository.update(timeEntryId, timeEntry);
        if(timeEntryUpdate == null)
            return ResponseEntity.ok(timeEntryUpdate);
        return ResponseEntity.ok(timeEntryUpdate);
    }

    public ResponseEntity<Void> delete(Long timeEntryId){
        timeEntryRepository.delete(timeEntryId);
        return null;
    }

}
