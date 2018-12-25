package mblog.nblog.service.impl;

import mblog.nblog.entity.IP;
import mblog.nblog.mapper.ipMapper;
import mblog.nblog.service.IPSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IPServiceImpl implements IPSerivce {

    @Autowired
    private ipMapper ipMapper;
    @Override
    public int saveIP(IP ip) {
        int id = ipMapper.saveIP(ip);
        return id;
    }
}
