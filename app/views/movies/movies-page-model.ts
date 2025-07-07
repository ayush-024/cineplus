import { Observable, Frame } from '@nativescript/core';
import { TMDBService, Movie } from '../../services/tmdb-service';

export class MoviesPageModel extends Observable {
  private tmdbService: TMDBService;
  private _isLoading: boolean = true;
  private _movies: Movie[] = [];
  private _selectedCategory: string = 'popular';

  constructor() {
    super();
    this.tmdbService = TMDBService.getInstance();
    this.loadMovies('popular');
  }

  get isLoading(): boolean {
    return this._isLoading;
  }

  set isLoading(value: boolean) {
    if (this._isLoading !== value) {
      this._isLoading = value;
      this.notifyPropertyChange('isLoading', value);
    }
  }

  get movies(): Movie[] {
    return this._movies;
  }

  set movies(value: Movie[]) {
    if (this._movies !== value) {
      this._movies = value.map(movie => ({
        ...movie,
        poster_path: this.tmdbService.getImageUrl(movie.poster_path)
      }));
      this.notifyPropertyChange('movies', this._movies);
    }
  }

  get selectedCategory(): string {
    return this._selectedCategory;
  }

  set selectedCategory(value: string) {
    if (this._selectedCategory !== value) {
      this._selectedCategory = value;
      this.notifyPropertyChange('selectedCategory', value);
    }
  }

  async loadMovies(category: string) {
    try {
      this.isLoading = true;
      this.selectedCategory = category;
      
      let movies: Movie[] = [];
      
      switch (category) {
        case 'popular':
          movies = await this.tmdbService.getPopularMovies();
          break;
        case 'top_rated':
          movies = await this.tmdbService.getTopRatedMovies();
          break;
        case 'upcoming':
          movies = await this.tmdbService.getUpcomingMovies();
          break;
      }
      
      this.movies = movies;
    } catch (error) {
      console.error('Error loading movies:', error);
    } finally {
      this.isLoading = false;
    }
  }

  navigateToMovieDetail(movie: Movie) {
    const frame = Frame.topmost();
    frame.navigate({
      moduleName: 'views/detail/detail-page',
      context: { content: movie, type: 'movie' }
    });
  }
}