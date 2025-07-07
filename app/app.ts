import { Application } from '@nativescript/core';
import { TMDBService } from './services/tmdb-service';

// Initialize TMDB service
TMDBService.getInstance();

Application.run({ moduleName: 'app-root' });