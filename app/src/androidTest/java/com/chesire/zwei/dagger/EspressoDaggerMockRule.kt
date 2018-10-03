package com.chesire.zwei.dagger

import androidx.test.InstrumentationRegistry.getInstrumentation
import com.chesire.zwei.MockApplication
import com.chesire.zwei.dagger.components.MockComponent
import com.chesire.zwei.dagger.modules.MockApplicationModule
import it.cosenonjaviste.daggermock.DaggerMock

fun espressoDaggerMockRule() = DaggerMock.rule<MockComponent>(MockApplicationModule()) {
    set {
        it.inject(app)
    }
    customizeBuilder<MockComponent.Builder> { it.create(app) }
}

val app: MockApplication
    get() = getInstrumentation().targetContext.applicationContext as MockApplication
