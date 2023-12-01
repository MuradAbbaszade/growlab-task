package az.growlabtask.service.impl;

import az.growlabtask.entity.Log;
import az.growlabtask.repository.LogRepository;
import az.growlabtask.service.LogService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
   private final  LogRepository logRepository;
    @Override
    public Log logAndSave(Log log) {
        logger.info(log.toString());
        return logRepository.save(log);
    }
}
