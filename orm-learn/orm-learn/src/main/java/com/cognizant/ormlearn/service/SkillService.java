package com.cognizant.ormlearn.service;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.repository.SkillRepository;

import ch.qos.logback.classic.Logger;

@Service
public class SkillService {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(SkillService.class);

	@Autowired
	private SkillRepository skillRepository;

	@Transactional
	public Skill get(int id) {
		LOGGER.info("Start");
		return skillRepository.findById(id).get();

	}

	@Transactional
	public void save(Skill skill) {
		LOGGER.info("Start");
		skillRepository.save(skill);
		LOGGER.info("End");
	}
}
