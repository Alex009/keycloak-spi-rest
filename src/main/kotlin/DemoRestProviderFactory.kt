package ru.alex009.keycloak

import org.keycloak.Config
import org.keycloak.models.KeycloakSession
import org.keycloak.models.KeycloakSessionFactory
import org.keycloak.services.resource.RealmResourceProvider
import org.keycloak.services.resource.RealmResourceProviderFactory

class DemoRestProviderFactory : RealmResourceProviderFactory {
    override fun create(session: KeycloakSession): RealmResourceProvider {
        return DemoRestProvider(session)
    }

    override fun init(config: Config.Scope?) {}
    override fun postInit(factory: KeycloakSessionFactory) {}
    override fun close() {}
    override fun getId(): String {
        return ID
    }

    companion object {
        const val ID = "alex009-rest-api"
    }
}
