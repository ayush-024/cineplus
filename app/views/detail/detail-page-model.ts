import { Observable, Frame } from '@nativescript/core';
import { TMDBService, Movie, TVShow } from '../../services/tmdb-service';

export class DetailPageModel extends Observable {
  private tmdbService: TMDBService;
  private _isLoading: boolean = true;
  private _content: any;
  private _type: string;
  private _genresText: string = '';

  constructor(content: any, type: string) {
    super();
    this.tmdbService = TMDBService.getInstance();
    this._content = {
      ...content,
      backdrop_path: this.tmdbService.getImageUrl(content.backdrop_path)
    };
    this._type = type;
    this.loadDetailedContent();
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

  get content(): any {
    return this._content;
  }

  set content(value: any) {
    if (this._content !== value) {
      this._content = value;
      this.notifyPropertyChange('content', value);
    }
  }

  get type(): string {
    return this._type;
  }

  get genresText(): string {
    return this._genresText;
  }

  set genresText(value: string) {
    if (this._genresText !== value) {
      this._genresText = value;
      this.notifyPropertyChange('genresText', value);
    }
  }

  private async loadDetailedContent() {
    try {
      this.isLoading = true;
      
      let detailedContent: any;
      
      if (this.type === 'movie') {
        detailedContent = await this.tmdbService.getMovieDetails(this.content.id);
      } else {
        detailedContent = await this.tmdbService.getTVShowDetails(this.content.id);
      }
      
      this.content = {
        ...detailedContent,
        backdrop_path: this.tmdbService.getImageUrl(detailedContent.backdrop_path)
      };
      
      // Set genres text
      if (detailedContent.genres && detailedContent.genres.length > 0) {
        this.genresText = detailedContent.genres.map(g => g.name).join(', ');
      }
      
    } catch (error) {
      console.error('Error loading detailed content:', error);
    } finally {
      this.isLoading = false;
    }
  }

  navigateBack() {
    const frame = Frame.topmost();
    frame.goBack();
  }

  playContent() {
    const frame = Frame.topmost();
    frame.navigate({
      moduleName: 'views/player/player-page',
      context: { content: this.content, type: this.type }
    });
  }

  addToList() {
    // TODO: Implement add to list functionality
    console.log('Adding to list...');
  }
}