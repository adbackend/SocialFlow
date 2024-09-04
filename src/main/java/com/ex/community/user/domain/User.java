package com.ex.community.user.domain;

import com.ex.community.common.domain.PositiveIntegerCounter;

import java.util.Objects;

public class User {

    private final Long id;
    private final UserInfo info;
    private final PositiveIntegerCounter followingCount; // 현재 사용자의 팔로우 수
    private final PositiveIntegerCounter followerCounter; // 현재 사용자의 팔로워 수

    public User(Long id, UserInfo userInfo) {

        if(userInfo == null){
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.info = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser){
        if(targetUser.equals(this)){
            throw new IllegalArgumentException();
        }
        followingCount.increase(); // 현재 사용자의 팔로우 수
        targetUser.increaseFollowerCount(); // 대상 사용자의 팔로워 수
    }

    public void unfollow(User targetUser){
        if(this.equals(targetUser)){
            throw new IllegalArgumentException();
        }
        followingCount.decrease();
        targetUser.decreaseFollowerCount();
    }

    private void increaseFollowerCount(){
        followerCounter.increase();
    }

    private void decreaseFollowerCount(){
        followerCounter.decrease();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId(){
        return id;
    }

    public int followerCount(){
        return followerCounter.getCount();
    }

    public int followingCount(){
        return followingCount.getCount();
    }

    public PositiveIntegerCounter getFollowingCount() {
        return followingCount;
    }

    public UserInfo getInfo() {
        return info;
    }
}
