package org.allRemindMeBot.daoImpl;

import org.allRemindMeBot.dao.BotUserApplicationDao;
import org.allRemindMeBot.entity.BotUserApplication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class BotUserApplicationDaoImpl implements BotUserApplicationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(BotUserApplication data) {
        this.entityManager.persist(data);
    }

    @Override
    @Transactional
    public Optional<BotUserApplication> findById(Integer appId) {
        Optional<BotUserApplication> application;
        try {
            application = Optional.of(entityManager.createQuery("SELECT APP FROM BotUserApplication APP WHERE APP.id = :appId", BotUserApplication.class)
                    .setParameter("appId", appId)
                    .getSingleResult());
        } catch (NoResultException exception) {
            application = Optional.empty();
        }
        return application;
    }

    @Override
    @Transactional
    public Optional<List<BotUserApplication>> findAll() {
        Optional<List<BotUserApplication>> applications;
        try {
            applications = Optional.of(entityManager.createQuery("SELECT APP FROM BotUserApplication APP", BotUserApplication.class)
                    .getResultList());
        } catch (NoResultException exception) {
            applications = Optional.empty();
        }
        return applications;
    }

    @Override
    @Transactional
    public Optional<List<BotUserApplication>> findAllBetweenDates(Date startDate, Date endDate) {
        Optional<List<BotUserApplication>> applications;
        try {
            applications = Optional.of(entityManager.createQuery("SELECT APP FROM BotUserApplication APP WHERE APP.dateApplication >= :startDate AND APP.dateApplication <= :endDate ORDER BY APP.dateApplication", BotUserApplication.class)
                    .setParameter("startDate", startDate).setParameter("endDate", endDate)
                    .getResultList());
        } catch (NoResultException exception) {
            applications = Optional.empty();
        }
        return applications;
    }

    @Override
    @Transactional
    public Optional<List<BotUserApplication>> findAllByChatId(Long chatId) {
        Optional<List<BotUserApplication>> applications;
        try {
            applications = Optional.of(entityManager.createQuery("SELECT APP FROM BotUserApplication APP WHERE APP.chatId = :chatId AND APP.dateApplication >= :date", BotUserApplication.class)
                    .setParameter("chatId", chatId).setParameter("date",new Date())
                    .getResultList());
        } catch (NoResultException exception) {
            applications = Optional.empty();
        }
        return applications;
    }

    @Override
    @Transactional
    public Optional<BotUserApplication> deleteApplicationById(Integer appId) {
        Optional<BotUserApplication> application = this.findById(appId);
        try {
            application.ifPresent(botUserApplication -> this.entityManager.createQuery("DELETE FROM BotUserApplication APP WHERE APP.id = :appId")
                    .setParameter("appId", botUserApplication.getId()).executeUpdate());
            return application;
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void delete(BotUserApplication data) {
        this.entityManager.remove(data);
    }

    @Override
    @Transactional
    public void deleteHistoryApplication(Date date, Long chatId) {
        this.entityManager.createQuery("DELETE FROM BotUserApplication APP WHERE APP.dateApplication <= :date AND APP.chatId = :chatId")
                .setParameter("date", date).setParameter("chatId",chatId).executeUpdate();
    }

    @Override
    @Transactional
    public void update(BotUserApplication data) {
    }
}
