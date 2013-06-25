# Puppeteer, a message-based controller framework for Android
======================================================================


## Message-Controller-View ("MVC") framework

### Package

> `org.ymkm.puppeteer`

> `org.ymkm.puppeteer.core`

> `org.ymkm.puppeteer.support`

### Description

Loosely coupled modules is a fundamental aspect of program design, which allows
applications to be extensible while minimizing side-effects on non-related modules.

Android applications tend to have strong coupling in various areas, e.g. UI elements defined (XML or through code)
VS what actions they do when triggered by the user (`onClick`, `onTouch`...) on the java side.
The logic in reactin to the event is written inside the registered Listener callback, while the action may be related
to something located in another module.

Ideally, UI-related classes should handle UI-related logic only, while delegating the non UI-specific
logic to an external entity. This allows decoupling of the UI from the actual application (business) logic.

MVC frameworks are popular because they allow exactly that : the View handles the "UI" while the Controller will take care of
the business logic, decide which View to render etc... The Model serves as a medium between Controller and View.

This framework aims at providing a similar approach to the MVC model, using Android-specific concepts.

The concept of `Fragment`, introduced in Android 3.0 helps with code reuse and acts as building blocks of UI in an `Activity`
(though a fragment may not always have a UI). The `Activity` thus became closer to what a Controller is in an MVC framework while the `Fragment` could be viewed as a View.

This framework goes further by also making the Activity part of the View, delegating the Controller logic to a separate component.

**How it works :**

* An `Activity` implementing `ControllableActivity` adds `Fragment` implementing `ControllableFragment` and registers any number of *controller callbacks*.

* When an activity becomes active, it registers itself to the *application controller* with a _controllable name_ which identifies it.

* Each added `ControllableFragment` gets assigned a unique *control ID* (integer) specific to this `ControllableActivity`.
`ControllableActivity` <=> `ControllableFragment` or `ControllableFragment` <=> `ControllableFragment` interaction
is done using these *control ID* instead of actual object instances.

* Each `ControllableFragment` possesses a *controllable fragment callback*.

* Decoupling between `ControllableActivity` and `ControllableFragment` is achieved using a messenging mechanism (using Android concepts such as `Handler` or `Messenger`)

* `ControllableActivity` or `ControllableFragment` can send *messages* to the *application controller*, which in turns
dispatches them to all *controller callbacks* registered by the currently running `ControllableActivity`.

* *controller callbacks* can then perform any non-UI logic based on the received message, then may dispatch some messages to any `ControllableFragment`, or to
the *application controller*. When targeting a `ControllableFragment`, it does so by using the *control ID* the fragment was added with by the `ControllableActivity`.

* Each of the messages that made it to `ControllableFragment` will in turn be handled by their *controllable fragment callback*.


**Benefits :**

* Abstracting interaction through messages and *control ID* removes the need to check for specific UI parts in reaction to certain events :
  i.e. A message may be sent to a non existing or removed `ControllableFragment`, or when no *controller callback* is registered by the `ControllableActivity`.
In that case, the message will simply be silently rejected.

* By registering new *controller callbacks* or adding new `ControllableFragment` to `ControllableActivity` dynamically enhances it with additional features in response to existing
behavior / events without changing the existing code.

* Existing `ControllableFragments` can be enhanced with new features in response to new messages simply by handling them in the *controllable fragment callback*.

* It avoids monolithic `Activity` or `Fragment` classes by splitting the logic in smaller parts.

* *controller callback* , `ControllableActivity` and `ControllableFragment` can each be developed as totally separate components, reused in other
`ControllableActivity` and `ControllableFragment`.

This package contains two versions of the framework :

* `puppeteer.*` uses the android 3.0+ API of `Fragment` while 

* `puppeteer.support.*` uses the V4 support library.

* `puppeteer.core.*` contains the common parts between both versions.


### Content

TODO

### Dependencies

* `android-support-v4` library for the `support` version.


# Licence
========================
Apache 2.0

# Author
========================
yoann@ymkm.org
