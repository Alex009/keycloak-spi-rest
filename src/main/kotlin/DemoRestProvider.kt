package ru.alex009.keycloak

import org.jboss.resteasy.annotations.cache.NoCache
import org.keycloak.OAuth2Constants
import org.keycloak.models.KeycloakContext
import org.keycloak.models.KeycloakSession
import org.keycloak.models.RealmModel
import org.keycloak.models.UserModel
import org.keycloak.services.resource.RealmResourceProvider
import org.keycloak.utils.MediaType
import java.util.Optional
import java.util.logging.Level
import java.util.logging.Logger
import javax.ws.rs.Encoded
import javax.ws.rs.NotFoundException
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces


class DemoRestProvider(
    private val session: KeycloakSession
) : RealmResourceProvider {

    @Produces(MediaType.APPLICATION_JSON)
    @NoCache
    @Path("reset-password")
    @POST
    @Encoded
    fun resetPassword(request: ResetPasswordRequest) {
        val logger = Logger.getLogger("009")

        logger.log(Level.WARNING, "request $request")

        val context: KeycloakContext = session.context
        val username: String = request.username!!
        val realm: RealmModel = context.realm
        val user: UserModel = session.userStorageManager().getUserByUsername(username, realm)
            ?: throw NotFoundException("user with username $username not found")

        logger.log(Level.WARNING, "user $user")

        user.addRequiredAction(UserModel.RequiredAction.UPDATE_PASSWORD)

        // session.userStorageManager().

        logger.log(Level.WARNING, "added required action")

        // idm-client needs to allow "Direct Access Grants: Resource Owner Password Credentials Grant"
//        val clientId = "iam-helper"
//        val clientSecret = "17e2017f-6db6-4a1e-b321-a41ffa25a092"

        // Client "idm-helper" needs service-account with at least "manage-users, view-clients, view-realm, view-users" roles for "realm-management"
//        val keycloak = KeycloakBuilder.builder() //
//            .serverUrl(context.authServerUrl.toString()) //
//            .realm(realm.name) //
//            .grantType(OAuth2Constants.CLIENT_CREDENTIALS) //
//            .clientId(clientId) //
//            .clientSecret(clientSecret)
//            .build()
//
//        keycloak
//            .realm(realm.name)
//            .users()
//            .get(user.id)
//            .executeActionsEmail(listOf("UPDATE_PASSWORD"))

//
//        // Get realm
//        val realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm())
//        val usersResource: UsersResource = realmResource.users()
//        val userResource: UserResource =
//            usersResource.get(currentUserProvider.getCurrentUser().getUserId())
//        val userRepresentation: UserRepresentation = userResource.toRepresentation()
//
//
//
//        val res: UserResource = UserResource(
//            realm,
//            user,
//
//        )
//        res.executeActionsEmail(null, null, 3600, listOf("UPDATE_PASSWORD"))

//        val validityInSecs: Int = realm
//            .getActionTokenGeneratedByUserLifespan(ResetCredentialsActionToken.TOKEN_TYPE)
//        val absoluteExpirationInSecs: Int = Time.currentTime() + validityInSecs

        user.addRequiredAction(UserModel.RequiredAction.UPDATE_PASSWORD)
//
//        val token = ResetCredentialsActionToken(
//            user.id,
//            absoluteExpirationInSecs,
//            authSessionEncodedId,
//            authenticationSession.getClient().getClientId()
//        )
//        val link: String = UriBuilder
//            .fromUri(
//                context.getActionTokenUrl(
//                    token.serialize(
//                        context.getSession(),
//                        context.getRealm(),
//                        context.getUriInfo()
//                    )
//                )
//            )
//            .build()
//            .toString()


//        val event = Event().apply {
//            type = EventType.SEND_RESET_PASSWORD
//            userId = user.id
//            details = mapOf(
//                Details.USERNAME to username,
//                Details.EMAIL to user.email
//            )
//        }
//            .user(user)
//            .detail(Details.USERNAME, username)
//            .detail(Details.EMAIL, user.email)
//            .detail(Details.CODE_ID, authenticationSession.getParentSession().getId()).success()
//
//        val reset = ResetCredentialEmail()
//        reset.authenticate()


//
//        val userModel = session.users().getUsers(
//            session.context.realm
//        )
//        return userModel.stream().map(Function<UserModel, Any> { e: UserModel ->
//            toUserDetail(
//                e
//            )
//        }).collect(Collectors.toList())
    }

    override fun close() {

    }

    override fun getResource(): Any = this
}

fun <T> Optional<T>.getOptional(): T? {
    return if (isPresent) get()
    else null
}
