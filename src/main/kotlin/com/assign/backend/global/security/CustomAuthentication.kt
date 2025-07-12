package com.assign.backend.global.security

import com.assign.backend.domain.user.entity.UserEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class CustomAuthentication(
    val user: UserEntity
) : Authentication {
    override fun getName(): String {
        return user.name
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return if (user != null) listOf(CustomAuthority.MEMBER) else emptyList()
    }

    override fun getCredentials(): Any {
        throw UnsupportedOperationException()
    }

    override fun getDetails(): Any {
        throw UnsupportedOperationException()
    }

    override fun getPrincipal(): UserEntity {
        return user
    }

    override fun isAuthenticated(): Boolean {
        return principal != null
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        throw UnsupportedOperationException()
    }
}