import { Observable, Frame } from '@nativescript/core';
import { TMDBService, TVShow } from '../../services/tmdb-service';

export class SeriesPageModel extends Observable {
  private tmdbService: TMDBService;
  private _isLoading: boolean = true;
  private _series: TVShow[] = [];
  private _selectedCategory: string = 'popular';

  constructor() {
    super();
    this.tmdbService = TMDBService.getInstance();
    this.loadSeries('popular');
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

  get series(): TVShow[] {
    return this._series;
  }

  set series(value: TVShow[]) {
    if (this._series !== value) {
      this._series = value.map(show => ({
        ...show,
        poster_path: this.tmdbService.getImageUrl(show.poster_path)
      }));
      this.notifyPropertyChange('series', this._series);
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

  async loadSeries(category: string) {
    try {
      this.isLoading = true;
      this.selectedCategory = category;
      
      let series: TVShow[] = [];
      
      switch (category) {
        case 'popular':
          series = await this.tmdbService.getPopularTVShows();
          break;
        case 'top_rated':
          series = await this.tmdbService.getTopRatedTVShows();
          break;
        case 'on_the_air':
          series = await this.tmdbService.getOnTheAirTVShows();
          break;
      }
      
      this.series = series;
    } catch (error) {
      console.error('Error loading series:', error);
    } finally {
      this.isLoading = false;
    }
  }

  navigateToSeriesDetail(series: TVShow) {
    const frame = Frame.topmost();
    frame.navigate({
      moduleName: 'views/detail/detail-page',
      context: { content: series, type: 'tv' }
    });
  }
}