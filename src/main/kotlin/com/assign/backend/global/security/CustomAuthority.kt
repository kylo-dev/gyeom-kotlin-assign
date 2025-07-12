package com.assign.backend.global.security

import org.springframework.security.core.GrantedAuthority

enum class CustomAuthority : GrantedAuthority {
    MEMBER,
    ADMIN,
    ;

    override fun getAuthority(): String {
        return name
    }

    companion object {
        fun getAuthority(isAdmin: Boolean): String {
            return if (isAdmin) ADMIN.name else MEMBER.name
        }
    }
}