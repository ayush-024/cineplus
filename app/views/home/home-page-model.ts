import { Observable, Frame } from '@nativescript/core';
import { TMDBService, Movie, TVShow } from '../../services/tmdb-service';

export class HomePageModel extends Observable {
  private tmdbService: TMDBService;
  private _isLoading: boolean = true;
  private _featuredContent: any = null;
  private _popularMovies: Movie[] = [];
  private _topRatedMovies: Movie[] = [];
  private _popularTVShows: TVShow[] = [];

  constructor() {
    super();
    this.tmdbService = TMDBService.getInstance();
    this.loadContent();
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

  get featuredContent(): any {
    return this._featuredContent;
  }

  set featuredContent(value: any) {
    if (this._featuredContent !== value) {
      this._featuredContent = value;
      this.notifyPropertyChange('featuredContent', value);
    }
  }

  get popularMovies(): Movie[] {
    return this._popularMovies;
  }

  set popularMovies(value: Movie[]) {
    if (this._popularMovies !== value) {
      this._popularMovies = value.map(movie => ({
        ...movie,
        poster_path: this.tmdbService.getImageUrl(movie.poster_path)
      }));
      this.notifyPropertyChange('popularMovies', this._popularMovies);
    }
  }

  get topRatedMovies(): Movie[] {
    return this._topRatedMovies;
  }

  set topRatedMovies(value: Movie[]) {
    if (this._topRatedMovies !== value) {
      this._topRatedMovies = value.map(movie => ({
        ...movie,
        poster_path: this.tmdbService.getImageUrl(movie.poster_path)
      }));
      this.notifyPropertyChange('topRatedMovies', this._topRatedMovies);
    }
  }

  get popularTVShows(): TVShow[] {
    return this._popularTVShows;
  }

  set popularTVShows(value: TVShow[]) {
    if (this._popularTVShows !== value) {
      this._popularTVShows = value.map(show => ({
        ...show,
        poster_path: this.tmdbService.getImageUrl(show.poster_path)
      }));
      this.notifyPropertyChange('popularTVShows', this._popularTVShows);
    }
  }

  async loadContent() {
    try {
      this.isLoading = true;
      
      const [popularMovies, topRatedMovies, popularTVShows] = await Promise.all([
        this.tmdbService.getPopularMovies(),
        this.tmdbService.getTopRatedMovies(),
        this.tmdbService.getPopularTVShows()
      ]);

      this.popularMovies = popularMovies;
      this.topRatedMovies = topRatedMovies;
      this.popularTVShows = popularTVShows;

      // Set featured content (first popular movie)
      if (popularMovies.length > 0) {
        this.featuredContent = {
          ...popularMovies[0],
          backdrop_path: this.tmdbService.getImageUrl(popularMovies[0].backdrop_path)
        };
      }

    } catch (error) {
      console.error('Error loading home content:', error);
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

  navigateToTVShowDetail(tvShow: TVShow) {
    const frame = Frame.topmost();
    frame.navigate({
      moduleName: 'views/detail/detail-page',
      context: { content: tvShow, type: 'tv' }
    });
  }

  playFeaturedContent() {
    if (this.featuredContent) {
      const frame = Frame.topmost();
      frame.navigate({
        moduleName: 'views/player/player-page',
        context: { content: this.featuredContent, type: 'movie' }
      });
    }
  }

  addToList() {
    // TODO: Implement add to list functionality
    console.log('Adding to list...');
  }
}