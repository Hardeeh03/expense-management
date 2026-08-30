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

## Daily Maintenance Log

- 2026-06-26 10:30:41 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-06-27 09:35:24 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-07-01 10:50:22 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-07-02 10:15:22 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-07-06 11:45:06 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-07-07 10:33:44 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-07-11 09:00:35 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-07-12 09:20:19 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-07-16 09:31:49 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-07-17 09:23:16 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-07-21 09:44:27 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-07-22 09:42:28 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-07-26 09:27:23 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-07-27 10:51:29 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-07-31 10:00:39 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-08-01 09:26:31 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-08-05 10:00:35 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-08-06 10:02:45 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-08-10 08:53:07 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-08-11 08:16:06 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-08-15 07:47:01 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-08-16 07:48:46 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-08-20 08:00:08 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-08-21 08:00:45 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-08-25 08:02:43 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-08-26 08:03:26 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
- 2026-08-30 12:59:19 UTC: automated maintenance check-in for `Hardeeh03/expense-management`.
