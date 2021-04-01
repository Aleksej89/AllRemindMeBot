package org.allRemindMeBot.daoImpl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.NonNull;
import org.allRemindMeBot.dao.BotUserDao;
import org.allRemindMeBot.entity.BotUser;
import org.allRemindMeBot.enums.AppCounters;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Repository
public class BotUserDaoImpl implements BotUserDao {

    private final LoadingCache<String, Optional<BotUser>> usersCache;

    {
        usersCache = CacheBuilder.newBuilder()
                .maximumSize(AppCounters.MAXIMUM_CACHE_COUNTER.getCounter())
                .expireAfterAccess(AppCounters.CACHE_EXPIRE_COUNTER.getCounter(), TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public Optional<BotUser> load(@NonNull String chatId) {
                        return getFromBase(Long.valueOf(chatId));
                    }
                });
    }

    @PersistenceContext
    private EntityManager entityManager;


    private Optional<BotUser> getFromBase(Long chatId) {
        Optional<BotUser> user;
        try {
            user = Optional.of(this.entityManager.createQuery("SELECT T FROM BotUser T where T.userChatId = :chatId", BotUser.class)
                    .setParameter("chatId", chatId).getSingleResult());
        } catch (NoResultException exception) {
            user = Optional.empty();
        }
        return user;
    }

    @Override
    @Transactional
    public void save(BotUser data) {
        this.entityManager.persist(data);
    }

    @Override
    @Transactional
    public Optional<BotUser> findById(Integer integer) {
        Optional<BotUser> user;
        try {
            user = Optional.of(this.entityManager.find(BotUser.class, integer));
        } catch (NoResultException exception) {
            user = Optional.empty();
        }
        return user;
    }

    @Override
    @Transactional
    public Optional<List<BotUser>> findAll() {
        Optional<List<BotUser>> users;
        try {
            users = Optional.of(this.entityManager.createQuery("SELECT user FROM BotUser user", BotUser.class).getResultList());
        } catch (NoResultException exception) {
            users = Optional.empty();
        }
        return users;
    }

    @Override
    @Transactional
    public void delete(BotUser data) {
    }

    @Override
    @Transactional
    public void update() {
    }

    @Override
    @Transactional
    public Optional<BotUser> findUser(BotUser botUser) {
        Optional<BotUser> user;
        try {
            user = this.usersCache.get(String.valueOf(botUser.getUserChatId()));
            if (user.isEmpty()) {
                this.usersCache.put(String.valueOf(botUser.getUserChatId()), Optional.of(botUser));
            }
        } catch (NoResultException | ExecutionException ex) {
            user = Optional.empty();
        }
        return user;
    }
}
