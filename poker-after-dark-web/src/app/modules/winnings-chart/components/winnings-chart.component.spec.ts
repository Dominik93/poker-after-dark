import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WinningsChartComponent } from './winnings-chart.component';

describe('WinningsChartComponent', () => {
  let component: WinningsChartComponent;
  let fixture: ComponentFixture<WinningsChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WinningsChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WinningsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
