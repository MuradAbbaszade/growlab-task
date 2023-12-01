package az.growlabtask.service;

import az.growlabtask.entity.Log;

public interface LogService {
    Log logAndSave(Log log);
}
