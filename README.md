# Expense Management (EXPENZO)

An Android expense tracker that helps you record credits and debits, track savings and emergency funds, and view basic statistics and reminders. The app uses local storage (SQLite + SharedPreferences) and supports multiple languages and light/dark mode.

## Features
- Local sign up / login with password reset
- Credit and debit tracking with categories, edit, and delete
- Savings tracker with running total
- Emergency fund amount tracker
- Calendar events with amount and purpose, plus daily reminders
- Basic statistics view (category totals and overall sums)
- Settings for notifications, language, and theme

## Tech Stack
- Kotlin
- AndroidX + Material Components
- SQLite (user accounts and calendar events)
- SharedPreferences (credit/debit/savings/emergency and settings)

## Project Structure
- `app/src/main/java/com/example/project/MainActivity.kt` - Navigation and fragment host
- `app/src/main/java/com/example/project/login.kt` - Login screen (launcher)
- `app/src/main/java/com/example/project/register.kt` - Registration screen
- `app/src/main/java/com/example/project/CreditFragment.kt` - Credit records
- `app/src/main/java/com/example/project/DebitFragment.kt` - Debit records
- `app/src/main/java/com/example/project/SavingsFragment.kt` - Savings tracking
- `app/src/main/java/com/example/project/EmergencyFragment.kt` - Emergency fund tracking
- `app/src/main/java/com/example/project/CalendarFragment.kt` - Calendar view
- `app/src/main/java/com/example/project/EventCreationFragment.kt` - Event creation/editing
- `app/src/main/java/com/example/project/NotifyFragment.kt` - Daily reminders
- `app/src/main/java/com/example/project/Statistics.kt` - Totals and category sums
- `app/src/main/java/com/example/project/SettingsFragment.kt` - Preferences (language/theme/notifications)

## Requirements
- Android Studio
- JDK 11 or 17
- Android SDK (compileSdk 34)
- Minimum Android version: API 26

## Setup
1. Clone the repo and open it in Android Studio.
2. Let Gradle sync finish.
3. Run the `app` configuration on an emulator or device.

### Command Line Build
```bash
./gradlew assembleDebug
```

## Notes
- User accounts and calendar events are stored in a local SQLite database.
- Credit, debit, savings, emergency values, and preferences are stored locally in SharedPreferences.
- A Firebase Auth dependency is included in `app/build.gradle`, but the current flows use local storage.

## Screens and Resources
App name is `EXPENZO` and resources include multiple locale strings under `app/src/main/res/values-*`.
