import { Observable, Frame } from '@nativescript/core';
import { TMDBService } from '../../services/tmdb-service';

export class PlayerPageModel extends Observable {
  private tmdbService: TMDBService;
  private _content: any;
  private _type: string;
  private _videoUrl: string = '';
  private _isPlaying: boolean = false;
  private _showControls: boolean = true;
  private _showSettings: boolean = false;
  private _currentTime: number = 0;
  private _duration: number = 0;
  private _currentTimeFormatted: string = '0:00';
  private _durationFormatted: string = '0:00';
  private _selectedQuality: string = 'auto';
  private _selectedAudio: string = 'en';
  private _selectedSubtitle: string = 'off';

  constructor(content: any, type: string) {
    super();
    this.tmdbService = TMDBService.getInstance();
    this._content = content;
    this._type = type;
    this.loadVideoUrl();
  }

  get content(): any {
    return this._content;
  }

  get type(): string {
    return this._type;
  }

  get videoUrl(): string {
    return this._videoUrl;
  }

  set videoUrl(value: string) {
    if (this._videoUrl !== value) {
      this._videoUrl = value;
      this.notifyPropertyChange('videoUrl', value);
    }
  }

  get isPlaying(): boolean {
    return this._isPlaying;
  }

  set isPlaying(value: boolean) {
    if (this._isPlaying !== value) {
      this._isPlaying = value;
      this.notifyPropertyChange('isPlaying', value);
    }
  }

  get showControls(): boolean {
    return this._showControls;
  }

  set showControls(value: boolean) {
    if (this._showControls !== value) {
      this._showControls = value;
      this.notifyPropertyChange('showControls', value);
    }
  }

  get showSettings(): boolean {
    return this._showSettings;
  }

  set showSettings(value: boolean) {
    if (this._showSettings !== value) {
      this._showSettings = value;
      this.notifyPropertyChange('showSettings', value);
    }
  }

  get currentTime(): number {
    return this._currentTime;
  }

  set currentTime(value: number) {
    if (this._currentTime !== value) {
      this._currentTime = value;
      this.notifyPropertyChange('currentTime', value);
      this.currentTimeFormatted = this.formatTime(value);
    }
  }

  get duration(): number {
    return this._duration;
  }

  set duration(value: number) {
    if (this._duration !== value) {
      this._duration = value;
      this.notifyPropertyChange('duration', value);
      this.durationFormatted = this.formatTime(value);
    }
  }

  get currentTimeFormatted(): string {
    return this._currentTimeFormatted;
  }

  set currentTimeFormatted(value: string) {
    if (this._currentTimeFormatted !== value) {
      this._currentTimeFormatted = value;
      this.notifyPropertyChange('currentTimeFormatted', value);
    }
  }

  get durationFormatted(): string {
    return this._durationFormatted;
  }

  set durationFormatted(value: string) {
    if (this._durationFormatted !== value) {
      this._durationFormatted = value;
      this.notifyPropertyChange('durationFormatted', value);
    }
  }

  get selectedQuality(): string {
    return this._selectedQuality;
  }

  set selectedQuality(value: string) {
    if (this._selectedQuality !== value) {
      this._selectedQuality = value;
      this.notifyPropertyChange('selectedQuality', value);
    }
  }

  get selectedAudio(): string {
    return this._selectedAudio;
  }

  set selectedAudio(value: string) {
    if (this._selectedAudio !== value) {
      this._selectedAudio = value;
      this.notifyPropertyChange('selectedAudio', value);
    }
  }

  get selectedSubtitle(): string {
    return this._selectedSubtitle;
  }

  set selectedSubtitle(value: string) {
    if (this._selectedSubtitle !== value) {
      this._selectedSubtitle = value;
      this.notifyPropertyChange('selectedSubtitle', value);
    }
  }

  private async loadVideoUrl() {
    try {
      const url = await this.tmdbService.getStreamingUrl(this.content.id, this.type);
      this.videoUrl = url;
    } catch (error) {
      console.error('Error loading video URL:', error);
      // Fallback to a sample video URL for demonstration
      this.videoUrl = 'https://sample-videos.com/zip/10/mp4/SampleVideo_1280x720_1mb.mp4';
    }
  }

  private formatTime(seconds: number): string {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = Math.floor(seconds % 60);
    return `${minutes}:${remainingSeconds.toString().padStart(2, '0')}`;
  }

  onVideoFinished() {
    this.isPlaying = false;
    this.currentTime = 0;
  }

  onVideoLoaded() {
    // Video metadata loaded - you can get duration here
    this.duration = 3600; // Placeholder - actual duration would come from video element
  }

  togglePlayPause() {
    this.isPlaying = !this.isPlaying;
    // Here you would control the actual video player
  }

  previousContent() {
    // TODO: Implement previous content functionality
    console.log('Previous content');
  }

  nextContent() {
    // TODO: Implement next content functionality
    console.log('Next content');
  }

  toggleSettings() {
    this.showSettings = !this.showSettings;
  }

  closePlayer() {
    const frame = Frame.topmost();
    frame.goBack();
  }

  seekTo(time: number) {
    this.currentTime = time;
    // Here you would seek the actual video player
  }

  changeQuality(quality: string) {
    this.selectedQuality = quality.toLowerCase();
    // Here you would change the video quality
    console.log('Quality changed to:', quality);
  }

  changeAudio(audio: string) {
    const audioMap = {
      'English': 'en',
      'Spanish': 'es',
      'French': 'fr'
    };
    this.selectedAudio = audioMap[audio] || 'en';
    // Here you would change the audio track
    console.log('Audio changed to:', audio);
  }

  changeSubtitle(subtitle: string) {
    const subtitleMap = {
      'Off': 'off',
      'English': 'en',
      'Spanish': 'es',
      'French': 'fr'
    };
    this.selectedSubtitle = subtitleMap[subtitle] || 'off';
    // Here you would change the subtitle track
    console.log('Subtitle changed to:', subtitle);
  }
}