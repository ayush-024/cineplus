# Disney+ Style Streaming App

A NativeScript Android streaming application built with Disney+ style UI/UX, featuring TMDB integration and ExoPlayer video playback.

## Features

- **Modern UI**: Disney+ inspired design with Material Design components
- **4 Main Sections**: Home, Movies, TV Series, and Search
- **TMDB Integration**: Real movie and TV show data from The Movie Database
- **Video Player**: ExoPlayer integration with quality, audio, and subtitle options
- **Responsive Design**: Optimized for various screen sizes
- **GitHub Actions**: Automated APK building

## Setup

1. **Get TMDB API Key**:
   - Visit [TMDB](https://www.themoviedb.org/settings/api)
   - Create an account and get your API key
   - Replace `YOUR_TMDB_API_KEY_HERE` in `app/services/tmdb-service.ts`

2. **Install Dependencies**:
   ```bash
   npm install
   ```

3. **Run the App**:
   ```bash
   ns run android
   ```

## Building APK

The project includes GitHub Actions workflow that automatically builds APK when you push to main/master branch.

### Manual Build

1. **Create Android Keystore**:
   ```bash
   keytool -genkey -v -keystore android.keystore -alias alias -keyalg RSA -keysize 2048 -validity 10000
   ```

2. **Build APK**:
   ```bash
   ns build android --release --key-store-path android.keystore --key-store-password password --key-store-alias alias --key-store-alias-password password
   ```

## Architecture

- **Views**: UI components for each section
- **Services**: API integration and data management
- **Models**: Data models and business logic
- **Components**: Reusable UI components

## Customization

- **Theme Colors**: Modify colors in `app/app.css`
- **API Integration**: Update `app/services/tmdb-service.ts`
- **Video Sources**: Implement your streaming service in `getStreamingUrl` method

## Requirements

- NativeScript CLI
- Android SDK
- Node.js 18+
- TMDB API Key

## License

MIT License