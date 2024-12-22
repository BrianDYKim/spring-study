package team.me.kotlinTx.presentation.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.me.kotlinTx.application.dto.CreateUserDto
import team.me.kotlinTx.application.dto.FindUserDto
import team.me.kotlinTx.application.dto.UpdateUserNameDto
import team.me.kotlinTx.application.service.UserService

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/user/register")
    fun register(@RequestBody request: CreateUserDto.Request): CreateUserDto.Response {
        return userService.createUser(request)
    }

    @PutMapping("/user/name")
    fun updateName(@RequestBody request: UpdateUserNameDto.Request): UpdateUserNameDto.Response? {
        return userService.updateName(request)
    }

    @PutMapping("/user/name/tx")
    fun updateNameWithTx(@RequestBody request: UpdateUserNameDto.Request): UpdateUserNameDto.Response? {
        return userService.updateNameWithTx(request)
    }

    @GetMapping("/user/{id}")
    fun findById(@PathVariable(name = "id") id: Long): FindUserDto.Response {
        val request = FindUserDto.Request(id)

        return userService.findById(request)
    }
}
